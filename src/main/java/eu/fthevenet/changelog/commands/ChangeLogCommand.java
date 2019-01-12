/*
 *    Copyright 2019 Frederic Thevenet
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.fthevenet.changelog.commands;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.pdf.converter.PdfConverterExtension;
import com.vladsch.flexmark.util.IRender;
import com.vladsch.flexmark.util.options.MutableDataSet;
import eu.fthevenet.changelog.github.GithubApi;
import eu.fthevenet.changelog.github.GithubRelease;
import org.apache.http.client.HttpResponseException;
import picocli.CommandLine;

import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.Callable;

@CommandLine.Command(description = "Create a changelog from a Github project's release history",
        name = "changelog", mixinStandardHelpOptions = true, version = "0.1")
public class ChangeLogCommand implements Callable<Void> {

    @CommandLine.Parameters(index = "0", description = "The repository's owner name")
    private String owner;
    @CommandLine.Parameters(index = "1", description = "The repository's name")
    private String repo;
    @CommandLine.Option(names = {"-t", "--oauthtoken"}, description = "An OAuth2 token to use to access Github API")
    private String oauthToken;
    @CommandLine.Option(names = {"-o", "--output"}, description = "The path to a file into which the changelog should be written")
    private Path output;
    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "AUTO", description = "The format for the changelog: Plain text (TXT), Markdown (MD), Html (HTML) or Pdf (PDF)")
    private OutputFormat format;
    @CommandLine.Option(names = {"-d", "--dateformat"}, defaultValue = "EEE, d MMM yyyy", description = "The date format to use (see https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html)")
    private SimpleDateFormat dateFormat;


    public static void main(String[] args) throws Exception {
        CommandLine cl = new CommandLine(new ChangeLogCommand());
        cl.registerConverter(SimpleDateFormat.class, SimpleDateFormat::new);
        cl.parseWithHandlers(
                new CommandLine.RunAll().andExit(0),
                CommandLine.defaultExceptionHandler().andExit(1),
                args);
    }

    @Override
    public Void call() throws Exception {
        try (OutputStream fout = (output == null) ? System.out : new FileOutputStream(output.toFile())) {
            if (format == OutputFormat.AUTO) {
                if (output != null) {
                    String fileName = output.getFileName().toString().toLowerCase();
                    if (fileName.endsWith(".htm") ||
                            fileName.endsWith(".html")) {
                        format = OutputFormat.HTML;
                    } else if (fileName.endsWith(".md")) {
                        format = OutputFormat.MD;
                    } else if (fileName.endsWith(".pdf")) {
                        format = OutputFormat.PDF;
                    } else {
                        format = OutputFormat.TXT;
                    }
                } else {
                    format = OutputFormat.TXT;
                }
            }
            switch (format) {
                case MD:
                    writeAsMarkdown(fout);
                    break;
                case TXT:
                    writeAsPlainText(fout);
                    break;
                case HTML:
                    writeAsHtml(fout);
                    break;
                case PDF:
                    writeAsPdf(fout);
                    break;
            }
        } catch (HttpResponseException e) {
            String msg;
            switch (e.getStatusCode()) {
                case 401:
                    msg = "Authentication failed while trying to access Github repo \"" + owner + "/" + repo + "\"";
                    break;
                case 403:
                    msg = "Access to Github repo \"" + owner + "/" + repo + "\" is denied.";
                    break;
                case 404:
                    msg = "Cannot find Github repo \"" + owner + "/" + repo + "\"";
                    break;
                case 500:
                    msg = "A server-side error has occurred while trying to access  Github repo \"" + owner + "/" + repo + "\": " + e.getMessage();
                    break;
                default:
                    msg = "Error executing HTTP request to Github repo \"" + owner + "/" + repo + "\": " + e.getMessage();
                    break;
            }
            throw new Exception(msg, e);
        } catch (ConnectException e) {
            throw new Exception(e.getMessage(), e);
        } catch (SSLHandshakeException e) {
            throw new Exception("An error occurred while negotiating connection security: " + e.getMessage(), e);
        }
        return null;
    }

    private void writeAsMarkdown(OutputStream out) throws IOException, URISyntaxException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
            for (GithubRelease release : GithubApi.getInstance().getAllReleases(owner, repo)) {
                writer.write("### [" + release.getName() + "](" + release.getHtmlUrl() + ")\n");
                writer.write("Released on " + dateFormat.format(release.getPublishedAt()) + "\n\n");
                writer.write(release.getBody());
                writer.write("\n\n");
            }
        }
    }

    private void writeAsPlainText(OutputStream out) throws IOException, URISyntaxException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
            for (GithubRelease release : GithubApi.getInstance().getAllReleases(owner, repo)) {
                writer.write(release.getName() + "\n" + release.getHtmlUrl() + "\n");
                writer.write("Released on " + dateFormat.format(release.getPublishedAt()) + "\n\n");
                writer.write(release.getBody());
                writer.write("\n\n");
            }
        }
    }

    private void writeAsHtml(OutputStream out) throws IOException, URISyntaxException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
            writer.write(renderToHtml());
        }
    }

    private void writeAsPdf(OutputStream out) throws IOException, URISyntaxException {
        PdfConverterExtension.exportToPdf(out, renderToHtml(), "", BaseRendererBuilder.TextDirection.LTR);
    }

    private String renderToHtml() throws IOException, URISyntaxException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            writeAsMarkdown(baos);
            MutableDataSet options = new MutableDataSet();
            options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
            options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");
            IRender renderer = HtmlRenderer.builder(options).build();
            Parser parser = Parser.builder(options).build();
            return renderer.render(parser.parseReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()))));
        }
    }

    public enum OutputFormat {
        TXT("Text"),
        MD("Markdown"),
        HTML("HTML"),
        PDF("PDF"),
        AUTO("Determine from output file name");

        String name;

        OutputFormat(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
