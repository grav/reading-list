# reading-list

Convert Safari's reading list to an html-file.

Nice if you want to access your reading list from a non-Apple device, eg. a Kindle.

## Usage

The tool takes a plist in XML format from standard in, and outputs an html file on standard out,
so you need to convert the plist file from binary with Apple's `plutil`:

```
$ plutil -convert xml1 -o - ~/Library/Safari/Bookmarks.plist | lein run > reading-list.html
```

The above one-liner will do the conversion, create the reading list as a web page, and output it to an html file.

This will generate an html file with links to all reading list entries.

## Acknowledgements

Uses [clj-plist](https://github.com/bdesham/clj-plist) for parsing the plist file.

## License

Copyright © 2016 Betafunk

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
