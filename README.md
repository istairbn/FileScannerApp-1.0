# FileScannerApp-1.0
A Logscape App that allows you to monitor Filesystem changes and modification dates.
######################################################################################################
# FileScannerApp-1.0
# Creator: Benjamin Newton
# Contact: ben.newton@excelian.com
# Intial Release Date: 26/05/2015
######################################################################################################

This is the FileScannerApp-1.0 - originally designed to work with the Logscape monitoring system.

This app is very simple. It looks in folders you designate and returns a log with the last modified time and the current size.
The Logscape .config files are configured to extrapolate further information from this data, including the time since the file was changed.

The main use case for this was working with multiple integrated systems. Often, System X relied on the output of System Y, so it became necessary to alert operators if a file had not been produced. Modified date was deemed a simple method of determining this - if known file name Z was modified more than 3 days ago, no new data has been produced in that time. Alerts can be simply configured from this data using Logscape. 

Files. 

FileScanner.groovy - The main script. No need to touch this, trust that it does it's thing. 
FileScannerApp-1.0.bundle - The file that configures which Hosts and how often the script will run. Check the logscape documentation for more on how bundles work. 

When configuring the XMl, each instance MUST have a target .properties file, see an example below. 
 <script>FileScanner.groovy config.properties</script>

This properties file should be placed in the lib folder - the default name is config.properties:
lib/config.properties

Why? Well because if you have multiple systems, you'll want to check different folders on different hosts at different time intervals. Therefore, the bundle file determines the hosts and time schedule, the .properties file determines the target paths.

Add targets as a JSON string, eg. { "Nickname1":"PathtoFolder1","TestFolder":"C:/Program Files/ExampleProgram/Output"}

You MUST use forward slashes, even if it is a Windows Path - because the Java Properties method escapes back slashes. 

The fields.config contains the Logscape watch directory and file type. 


Good luck!
