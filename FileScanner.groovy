/*
FileScanner.groovy
Creator:Ben Newton
Contact: ben.newton@excelian.com
 */
import groovy.json.*

def props = args.length > 0 ? ".\\lib\\" + args[0] : "\\lib\\config.properties"

Properties properties
properties = new Properties()
File propertiesFile
propertiesFile = new File(props)

if (propertiesFile.exists()){
    propertiesFile.withInputStream{
        properties.load(it)
    }
}
else{
    println "No config.properties File Present in lib folder"
    println System.getProperty("user.dir")
    return //1
}

def now = new Date().format("yyyy/MM/dd HH:mm:ss:SSS ZZZ")

def jsonSlurper = new JsonSlurper()
def source = properties.targets
def targets = jsonSlurper.parseText(source)
assert targets instanceof Map
def fileList = []
targets.each { folder ->
    File file = new File(folder.value)
    if (file.exists()) {
        file.eachFile{it ->
            def modified = new Date(it.lastModified()).format("yyyy/MM/dd HH:mm:ss:SSS ZZZ")
            def size = it.length()
            println "$now,INFO,Name:$folder.key,File:$it.absolutePath,Filename:$it.name,ModifiedDate:$modified,Bytes:$size"
        }
        //fileList << file.absolutePath
    } else {
        println "$now,WARN,Name:$folder.key,File:$folder.value,fileNotFoundException:Missing File"
    }
}
