package com.code.demo.ftpupload.service.impl;

import com.code.demo.ftpupload.service.StorageService;
import com.code.demo.ftpupload.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class StorageServiceImpl implements StorageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private FtpUtil ftpUtil;

    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${ftp.user}")
    private String ftpUser;
    @Value("${ftp.password}")
    private String ftpPassWord;


    @Override
    public boolean uploadFile(String remoteDirPath, String remoteFileName, String uploadFilePath) {
        FTPClient ftpClient = new FTPClient();
        try {
            logger.info("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.info("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean result = ftpUtil.uploadFile(ftpClient, remoteDirPath, remoteFileName, uploadFilePath);
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean uploadDir(String uploadDirPath, String remoteDirPath) {
        FTPClient ftpClient = new FTPClient();
        try {
            logger.debug("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.debug("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean result = ftpUtil.uploadDirectory(ftpClient, uploadDirPath, remoteDirPath);

            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean downloadFile(String remoteDirPath, String remoteFileName, Object parameter) {
        FTPClient ftpClient = new FTPClient();
        try {
            logger.debug("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.debug("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean result = false;
            if (parameter instanceof String) {
                result = ftpUtil.downloadFile(ftpClient, remoteDirPath, remoteFileName, (String) parameter);
            } else if (parameter instanceof OutputStream) {
                result = ftpUtil.downloadFile(ftpClient, remoteDirPath, remoteFileName, (OutputStream) parameter);
            } else {
                logger.info("download failed; parameter: {} type is not support", parameter);
            }
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean downloadDirectory(String remoteDirPath, String localDirPath) {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            logger.debug("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.debug("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            result = ftpUtil.downloadDirectory(ftpClient, remoteDirPath, localDirPath);
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteFile(String remoteFilePath) {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            logger.debug("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.debug("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            result = ftpUtil.deleteFile(ftpClient, remoteFilePath);
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteDirectory(String remoteDirPath) {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            logger.debug("connecting... ftp server: {}:{}; And logging with user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassWord);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed... ftp server: {}:{}", ftpHost, ftpPort);
            }
            logger.debug("connect and login successful with: {}:{} => user: {}", ftpHost, ftpPort, ftpUser);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            result = ftpUtil.deleteDirectory(ftpClient, remoteDirPath);
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }
}
