# driver-onboard
You are part of a team, building a ride management application like Uber. You are responsible for the
driver onboarding module. As part of this module you are required to build an interface that will allow a
driver to sign-up as a driver partner. The driver onboarding module should:
 -   a) Allow a driver to sign-up and enter their profile information
 -   b) Trigger onboarding processes like document collection, background verification, shipping of tracking device, etc.,
 -   c) Allow a driver to mark when they are ready to take a ride

Please create a list of the API interfaces you will expose to support these requirements and also come up
with choices of data stores, tech stacks, etc.,
Instructions to the candidate:
 -    1. We are interested in your approach to the problem. It is alright if you don’t have a beautiful PPT
 -    2. You can even use pen/paper to draw your design and take a pic of that and share it as part of your craft demo
 -    3. Do take the time to clearly define the data models and the API interfaces.

## How to run
To install and add maven to environment variable visit [Install maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) if you are under a proxy check out [add proxy to maven](https://stackoverflow.com/a/47175285/8149916)

Install mysql on your system and create database named driver, set the user and password in [application.properties](src/main/resources/application.properties) file

Navigate to the root of the project via command line and execute the command
`mvn spring-boot:run`
