/*
 *    Copyright 2017-2018 Frederic Thevenet
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

package eu.fthevenet.changelog.github;


import com.google.gson.annotations.SerializedName;
import eu.fthevenet.changelog.version.Version;

import java.util.Date;


/**
 * GitHub release POJO
 *
 * @author Frederic Thevenet
 */
public class GithubRelease {
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("assets_url")
    private String assetsUrl;
    @SerializedName("upload_url")
    private String uploadUrl;
    @SerializedName("tarball_url")
    private String tarballUrl;
    @SerializedName("zipball_url")
    private String zipballUrl;
    private long id;
    @SerializedName("tag_name")
    private String tagName;
    @SerializedName("target_commitish")
    private String targetCommitish;
    private String name;
    private String body;
    @SerializedName("draft")
    private boolean isDraft;
    @SerializedName("prerelease")
    private boolean isPrerelease;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("published_at")
    private Date publishedAt;
    private GithubUser author;

    /**
     * Returns the url associated to the release.
     *
     * @return the url associated to the release.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets  the url associated to the release.
     *
     * @param url the url associated to the release.
     * @return this release
     */
    public GithubRelease setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Returns the url associated to the html page.
     *
     * @return htmlUrl the url associated to the html page.
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Sets the url associated to the html page.
     *
     * @param htmlUrl the url associated to the html page.
     * @return this release
     */
    public GithubRelease setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    /**
     * Returns the assets url
     *
     * @return the assets url
     */
    public String getAssetsUrl() {
        return assetsUrl;
    }

    /**
     * Sets the assets url
     *
     * @param assetsUrl the assets url
     * @return this release
     */
    public GithubRelease setAssetsUrl(String assetsUrl) {
        this.assetsUrl = assetsUrl;
        return this;
    }

    /**
     * Returns the upload url
     *
     * @return the upload url
     */
    public String getUploadUrl() {
        return uploadUrl;
    }

    /**
     * Sets the upload url
     *
     * @param uploadUrl the upload url
     * @return this release
     */
    public GithubRelease setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
        return this;
    }

    /**
     * Retuns the tarball url
     *
     * @return the tarball url
     */
    public String getTarballUrl() {
        return tarballUrl;
    }

    /**
     * Sets the tarball url
     *
     * @param tarballUrl the tarball url
     * @return this release
     */
    public GithubRelease setTarballUrl(String tarballUrl) {
        this.tarballUrl = tarballUrl;
        return this;
    }

    /**
     * Returns the zip url.
     *
     * @return zipballUrl the zip url.
     */
    public String getZipballUrl() {
        return zipballUrl;
    }

    /**
     * Sets the zip url.
     *
     * @param zipballUrl the zip url.
     * @return this release
     */
    public GithubRelease setZipballUrl(String zipballUrl) {
        this.zipballUrl = zipballUrl;
        return this;
    }

    /**
     * Returns the id of the release
     *
     * @return id the id of the release
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the release
     *
     * @param id the id of the release
     * @return this release
     */
    public GithubRelease setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the tag name.
     *
     * @return tagName the tag name.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the tag name.
     *
     * @param tagName the tag name.
     * @return this release
     */
    public GithubRelease setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    /**
     * Returns the target commitish
     *
     * @return targetCommitish  the target commitish
     */
    public String getTargetCommitish() {
        return targetCommitish;
    }

    /**
     * Sets  the target commitish
     *
     * @param targetCommitish the target commitish
     * @return this release
     */
    public GithubRelease setTargetCommitish(String targetCommitish) {
        this.targetCommitish = targetCommitish;
        return this;
    }

    /**
     * Returns the name of the release.
     *
     * @return name the name of the release.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the release.
     *
     * @param name the name of the release.
     * @return this release
     */
    public GithubRelease setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the body of the release.
     *
     * @return body  the body of the release.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets  the body of the release.
     *
     * @param body the body of the release.
     * @return this release
     */
    public GithubRelease setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * Returns true is the release is a draft, false otherwise.
     *
     * @return isDraft true is the release is a draft, false otherwise.
     */
    public boolean isDraft() {
        return isDraft;
    }

    /**
     * Sets to true is the release is a draft, false otherwise.
     *
     * @param isDraft true is the release is a draft, false otherwise.
     * @return this release
     */
    public GithubRelease setDraft(boolean isDraft) {
        this.isDraft = isDraft;
        return this;
    }

    /**
     * Returns true if the release is a pre-release, false otherwise.
     *
     * @return isPrerelease  true is the release is a pre-release, false otherwise.
     */
    public boolean isPrerelease() {
        return isPrerelease;
    }

    /**
     * Sets to true if the release is a pre-release, false otherwise.
     *
     * @param isPrerelease true if the release is a pre-release, false otherwise.
     * @return this release
     */
    public GithubRelease setPrerelease(boolean isPrerelease) {
        this.isPrerelease = isPrerelease;
        return this;
    }

    /**
     * Returns the creation date.
     *
     * @return createdAt the creation date.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation date.
     *
     * @param createdAt the creation date.
     * @return this release
     */
    public GithubRelease setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Returns the publication date.
     *
     * @return publishedAt the publication date.
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets  the publication date.
     *
     * @param publishedAt the publication date.
     * @return this release
     */
    public GithubRelease setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    /**
     * Returns the author of the release.
     *
     * @return author the author of the release.
     */
    public GithubUser getAuthor() {
        return author;
    }

    /**
     * Sets  the author of the release.
     *
     * @param author the author of the release.
     * @return this release
     */
    public GithubRelease setAuthor(GithubUser author) {
        this.author = author;
        return this;
    }

    /**
     * Returns the version of the release.
     *
     * @return the version of the release.
     */
    public Version getVersion() {
        if (tagName == null) {
            return Version.emptyVersion;
        }
        try {
            return new Version(tagName.replaceAll("^v", ""));
        } catch (IllegalArgumentException e) {
            return Version.emptyVersion;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GithubRelease{");
        sb.append("url='").append(url).append('\'');
        sb.append(", htmlUrl='").append(htmlUrl).append('\'');
        sb.append(", assetsUrl='").append(assetsUrl).append('\'');
        sb.append(", uploadUrl='").append(uploadUrl).append('\'');
        sb.append(", tarballUrl='").append(tarballUrl).append('\'');
        sb.append(", zipballUrl='").append(zipballUrl).append('\'');
        sb.append(", id=").append(id);
        sb.append(", tagName='").append(tagName).append('\'');
        sb.append(", targetCommitish='").append(targetCommitish).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", isDraft=").append(isDraft);
        sb.append(", isPrerelease=").append(isPrerelease);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", publishedAt=").append(publishedAt);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
