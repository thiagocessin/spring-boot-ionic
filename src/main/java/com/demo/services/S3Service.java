package com.demo.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {

		try {
			File file = new File(localFilePath);
			LOG.error("Iniciando UPLOAD.. ");

			s3client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));

			LOG.error("UPLOAD finalizado");

		} catch (AmazonServiceException e) {
			LOG.error("AmazonServiceException: " + e.getErrorMessage());

			LOG.error("AmazonServiceExceptionCODE: " + e.getErrorCode());

		} catch (AmazonClientException e) {

			LOG.error("AmazonClientException: " + e.getMessage());

			LOG.error("AmazonClientException: " + e.getMessage());

		}

	}

}
