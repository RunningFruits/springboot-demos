package com.code.demo.http.service;


public interface StorageService {

    /**
     * 存储文件
     *
     * @param remoteDirPath: 远端存储目录
     * @param remoteFileName: 远端存储文件名
     * @param uploadFilePath: 待上传文件路径
     * @return
     */
    boolean uploadFile( String remoteDirPath, String remoteFileName, String uploadFilePath);

    /**
     * 存储目录
     *
     * @param uploadDirPath: 待上传目录
     * @param remoteDirPath: 远端存储目录
     * @return
     */
    boolean uploadDir( String uploadDirPath, String remoteDirPath);

    /**
     * 下载文件到本地文件
     *
     * @param remoteDirPath
     * @param remoteFileName
     * @param parameter: 可以为String或者OutputStream
     * @return
     */
    boolean downloadFile( String remoteDirPath, String remoteFileName, Object parameter);

    /**
     * 下载目录; 递归下载子目录下的文件
     *
     * @param remoteDirPath
     * @param localDirPath
     * @return
     */
    boolean downloadDirectory( String remoteDirPath, String localDirPath);

    /**
     * 删除文件
     *
     * @param remoteFilePath
     * @return
     */
    boolean deleteFile( String remoteFilePath);

    /**
     * 删除目录; 递归删除子目录
     *
     * @param remoteDirPath
     * @return
     */
    boolean deleteDirectory(String remoteDirPath);
}
