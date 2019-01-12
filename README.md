# changelog-maker

A command line tool that creates a changelog from a Github project's release history.

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

