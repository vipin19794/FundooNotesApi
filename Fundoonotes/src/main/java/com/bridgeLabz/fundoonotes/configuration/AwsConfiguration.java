package com.bridgeLabz.fundoonotes.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfiguration {

	@Value("${aws.access.key.secret}")
	private String awsKeySecretId;

	@Value("${aws.access.key.id}")
	private String accessKey;

	@Value("${aws.region}")
	private String region;

	@Bean
	public AmazonS3 awsS3Client() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, awsKeySecretId);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
	}
}