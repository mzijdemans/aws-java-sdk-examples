# aws-java-sdk-examples
Working examples of a recent aws java SDK

When running these examples within AWS, for example EC2 machines fo Lambda, 
you should appoint roles to these instances. The example code will then use those roles automatically.
Role can be managed at AWS IAM in the management console.
see: https://console.aws.amazon.com/console/home
and: https://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html?icmpid=docs_iam_console

When running this code locally, first of all start by installing and configuring awscli, 
the Amazon Webservices Commandline Interface

source: https://docs.aws.amazon.com/cli/latest/userguide/installing.html

basically,
1. install python (3.3+)
2. install pip
3. install awscli, via:
    $ pip install awscli --upgrade --user
    this can also be used to update the awscli
4. run,
    $ aws configure
    enter your account keys here, which can be create at AWS IAM in the management console
    https://console.aws.amazon.com/console/home
    https://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html?icmpid=docs_iam_console
    https://aws.amazon.com/sdk-for-java/
    
#Tip #1
use maven the import aslo the sources into your IDE, this way you can navigate to the source code 
Amazon has provided nice Javadocs with all classes and functions.

#Java
We used JDK 10 while creating these examples.
  
#The Examples
We based these examples on our own work using AWS. We found that is was sometimes not very easy to find a simple and 
good example of code using a recent version of the AWS Java SDK.
The POM file contains a recent version, note the around 10 new versions are released each month, so keep updating!

#HELP!
feel free to provide simple examples of missing services using pull requests, we are not that strict in accepting code,
the package structure is devided among the services, keep it simple, one flow/example per function, if possible.
(better examples are always welcome!) 

#Sources
NOTE:
all example are created based on the AWS documentation https://docs.aws.amazon.com/
sometimes I copied this, but nearly always this was outdated... so i updated it

and on the AWS Java SDK API documentation, https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/

VERSION 2 Developer Preview.
