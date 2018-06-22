package com.shopsuite.examples.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class S3 {
    public PutObjectResult uploadFile() {

        //All current AWS Java SDK clients can now work with a Builder, which can create a default client.
        //this default client will use the credentials of the (OS) user running this program.
        //locally this will use the credentials entered using "$ aws configure"
        //on an aws instance the assigned role will be magically used...
        //well, not magically, the assigned roles credentials are just placed at the same place as they locally would...
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        // we use java.io.File to read a file from an absolute path.
        File file = new File("/tmp/example.jpg");

        //next we define the bucketname, as defined within the AWS S3 management console.
        //buckets are the main containers within S3
        //this example's url would be: s3://example-bucket-name/...
        String bucketName     = "example-bucket-name";

        //now we need a key name, which is kind of like an absolute file path within the bucket
        //if you use this bucket as a blob-store next to a database, working with id's might be an option
        //but using S3 as a backend for a CDN, than succint real names might be better...
        String keyName        = "/images/example.jpg";

        //The current version of the SDK provides neat request objects to use a kind of command, request or filter
        //the PutObjectRequest takes several kind of parameters, the bucketname, the keyname and the file
        //note: this File object could also be a URL, or inputstream
        PutObjectRequest putRequest = new PutObjectRequest(bucketName, keyName, file);

        //and finally, put the object
        //this in kind will return a result object, as do a lot of calls to AWS
        //such results as this one contain information about what happened. And about the object put on S3,
        //for example an md5 hash to check the file, the eTag, version information, have a look at the S3 management
        //pages, you will find related terms there.
        PutObjectResult putResult = s3client.putObject(putRequest);

        //show the MD5 hash
        System.out.println(putResult.getContentMd5());

        //get the eTag
        System.out.println(putResult.getETag());

        //version id, if versioning is enabled on S3 (keeps old version, according to set restrictions)
        System.out.println(putResult.getVersionId());

        //also all kinds of meta data is available. for example:
        System.out.println(putResult.getMetadata().getContentType());

        //we skipped one thing in this example.
        //access control
        //see the next function example

        return putResult;
    }

    public PutObjectResult uploadFileWithACLOptions() {
        // example found at: https://docs.aws.amazon.com/AmazonS3/latest/dev/acl-using-java-sdk.html

        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
        File file = new File("/tmp/example.jpg");
        String bucketName     = "example-bucket-name";
        String keyName        = "/images/example.jpg";
        PutObjectRequest putRequest = new PutObjectRequest(bucketName, keyName, file);

        //if the bucket is public, this is easy for the part where everybody can read the object.
        //if you use 1 role within this particular bucket, it is quite easy aswell..
        AccessControlList bucketAcl = s3client.getBucketAcl(bucketName);

        //you could change the ownership, cannonical user id's can be found within s3 permission management
        //acl.setOwner(new Owner("s3-cannonical-id", "a-display-name"));

        Collection<Grant> grantCollection = new ArrayList<Grant>();

        // Grant the account owner full control.
        Grant grant1 = new Grant(new CanonicalGrantee(s3client.getS3AccountOwner().getId()), Permission.FullControl);
        grantCollection.add(grant1);

        // Grant the LogDelivery group permission to write to the bucket.
        Grant grant2 = new Grant(GroupGrantee.LogDelivery, Permission.Write);
        grantCollection.add(grant2);

        // Save (replace) grants by deleting all current ACL grants and replacing
        // them with the two we just created.
        bucketAcl.getGrantsAsList().clear();
        bucketAcl.getGrantsAsList().addAll(grantCollection);

        // and eventually add this to the file upload
        putRequest.setAccessControlList(bucketAcl);

        //or use
        //s3client.setBucketAcl(bucketName, bucketAcl);
        //to change the complete bucket ACL... carefully...


        PutObjectResult putResult = s3client.putObject(putRequest);


        return putResult;
    }
}
