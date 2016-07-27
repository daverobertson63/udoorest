
# Project Title

UDOO Quad/Dual REST based framework using Spark Java.  This project uses the Spark Java framework to provide the basis of simple RESTful commands
to control the pins on your UDOO board. This is a work in progress and implements the setting and reading of pins on the Quad/Dual board. 


## Getting Started

Since you will probably want to do your own thing - rather than a jar - I suggest you just download or fork the project and load into Eclipse.  

I based this on a Java Spark project

### Prerequisities

Java 8 is a must on the compliation IDE and the UDOO.  I was able to use openjdk for this.


### Installing

Just import the project.  You can make a runnable jar, port that to the udoo and run it.  Default port is 4567.

There are two main rourtes to use based on the IP of the UDOO

http://192.168.178.53:4567/gpiowrite/12/1 - which writes ON to Pin 12
http://192.168.178.53:4567/gpioread/12 - which returns a json string thus

{"id":12,"value":0,"uri":"/sys/class/gpio/gpio3/","currentPinState":"LOW","currentPinMode":"OUTPUT"}

And so on... 



## Deployment

I deployed a quadrest.jar file and just run it with java -jar quadrest.java - build a service round it if you wish

## Built With

Spark Java - ( Not Apache )
Mavan for the Java Spark default project framework
Serial java lib  - rxtx 2.1.7

## Contributing

## Versioning

## Authors


Dave Robertson

## License

This project is licensed under the MIT License

## Acknowledgments

* Java Spark guys for an awesomely simple framework

