# data_server
This is a UDP server application with a simple shell interface, written in the Spring Framework. The server uses PostgreSQL as the database system.<p>
The server receives a string containing readings of an MPU6050 sensor sent from a UDP client. The data in the string is parsed into 6 fields:
* Data ID.
* Sending Time.
* Acceleration along X axis.
* Rotation speed around X axis.
* Acceleration along Y axis.
* Rotation speed around Y axis.
* Acceleration along Z axis.
* Rotation speed around Z axis.
* Temperature.

The following commands are supported:
* export: exports the data in the database to a .xlsx file.
* clear-data: clear the database.
