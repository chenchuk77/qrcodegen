# qrcodegen
#
##################################################

This application with encode a string (typically web link) to a QR image for android.
the application also prints the QR image into a non graphic terminal by using ascii chars, The idea is to use it in Jenkins job to generate links to build artifacts. This may be helpful for fast installation of android debug APK's during development.

- clone the project
- from root folder run mvn clean package
- cd target
- run the main() from QRGen, provide 2 args :
  - url: the url to encode (any string is a valid input)
  - filename: absolute/path/to/file.png

EXAMPLE: 

- ${PROJ_ROOT}/target/$ java -Durl=http://1.2.3 -Dfilename=333.png -cp qrgen-1.0-SNAPSHOT-jar-with-dependencies.jar net.kukinet.qr.QRGen



