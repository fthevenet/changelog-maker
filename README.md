# changelog-maker

[![Build Status](https://img.shields.io/travis/fthevenet/changelog-maker.svg?logo=travis&logoColor=white&style=flat-square)](https://travis-ci.org/fthevenet/changelog-maker)
[![Maven Central](https://img.shields.io/maven-central/v/eu.fthevenet/changelog-maker.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22eu.fthevenet%22%20AND%20a:%22changelog-maker%22)

A command line tool that creates a changelog from a Github project's release history.

It works by pulling all the releases for a specified repository and write a new file with the name, version, date and description of each releases, in a choice of formats (Plain text, Markdown, HTML or PDF).

    Usage: changelog [-hV] [-d=<dateFormat>] [-f=<format>] [-o=<output>]
                     [-t=<oauthToken>] <owner> <repo>
    Create a changelog from a Github project's release history
          <owner>             The repository's owner name
          <repo>              The repository's name
      -d, --dateformat=<dateFormat>
                              The date format to use (see https://docs.oracle.
                                com/javase/8/docs/api/java/text/SimpleDateFormat.html)
      -f, --format=<format>   The format for the changelog: Plain text (TXT), Markdown
                                (MD), Html (HTML) or Pdf (PDF)
      -h, --help              Show this help message and exit.
      -o, --output=<output>   The path to a file into which the changelog should be
                                written
      -t, --oauthtoken=<oauthToken>
                              An OAuth2 token to use to access Github API
      -V, --version           Print version information and exit.

