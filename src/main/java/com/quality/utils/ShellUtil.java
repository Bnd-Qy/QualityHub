package com.quality.utils;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
public class ShellUtil {
    volatile Map<String, SshSession> SESSION_MANAGE = new HashMap<>();


    @Value("${ssh.strictHostKeyChecking:no}")
    private String strictHostKeyChecking;

    @Value("${ssh.timeout:30000}")
    private Integer timeout;


    /**
     * 初始化
     *
     * @param ip       远程主机IP地址
     * @param port     远程主机端口
     * @param username 远程主机登陆用户名
     * @param password 远程主机登陆密码
     * @throws JSchException JSch异常
     */
    public void init(String ip, Integer port, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, ip, port);
        session.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", strictHostKeyChecking);
        session.setConfig(sshConfig);
        session.connect(timeout);
        //缓存会话
        SshSession sshSession = new SshSession();
        sshSession.setSession(session);
        SESSION_MANAGE.put(ip, sshSession);
        log.info("Session connected!");
    }

    public void init(String ip, String username, String password) throws JSchException {
        init(ip, 22, username, password);
    }

    /**
     * 连接多次执行命令，执行命令完毕后需要执行close()方法
     *
     * @param command 需要执行的指令
     * @return 执行结果
     * @throws Exception 没有执行初始化
     */
    public String execCmd(String ip, String command) throws Exception {
        initChannelExec(ip);
        log.info("${} > {}",ip, command);
        SshSession sshSession = SESSION_MANAGE.get(ip);
        ChannelExec channelExec = sshSession.getChannelExec();
        Channel channel = sshSession.getChannel();
        channelExec.setCommand(command);
        channel.setInputStream(null);
        channelExec.setErrStream(System.err);
        channel.connect();
        StringBuilder sb = new StringBuilder(16);
        try (InputStream in = channelExec.getInputStream();
             InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                sb.append("\n").append(buffer);
            }
//            log.info("execCmd result - > {}", sb);
            return sb.toString();
        }
    }


    /**
     * 执行命令关闭连接
     *
     * @param command 需要执行的指令
     * @return 执行结果
     * @throws Exception 没有执行初始化
     */
//    public String execCmdAndClose(String command) throws Exception {
//        String result = execCmd(command);
//        close();
//        return result;
//    }

    /**
     * 执行复杂shell命令
     *
     * @param cmds 多条命令
     * @return 执行结果
     * @throws Exception 连接异常
     */
//    public String execCmdByShell(String... cmds) throws Exception {
//        return execCmdByShell(Arrays.asList(cmds));
//    }

    /**
     * 执行复杂shell命令
     *
     * @param cmds 多条命令
     * @return 执行结果
     * @throws Exception 连接异常
     */
//    public String execCmdByShell(List<String> cmds) throws Exception {
//        String result = "";
//        initChannelShell();
//        InputStream inputStream = channelShell.getInputStream();
//        channelShell.setPty(true);
//        channelShell.connect();
//
//        OutputStream outputStream = channelShell.getOutputStream();
//        PrintWriter printWriter = new PrintWriter(outputStream);
//        for (String cmd : cmds) {
//            printWriter.println(cmd);
//        }
//        printWriter.flush();
//
//        byte[] tmp = new byte[1024];
//        while (true) {
//
//            while (inputStream.available() > 0) {
//                int i = inputStream.read(tmp, 0, 1024);
//                if (i < 0) {
//                    break;
//                }
//                String s = new String(tmp, 0, i);
//                if (s.contains("--More--")) {
//                    outputStream.write((" ").getBytes());
//                    outputStream.flush();
//                }
//                System.out.println(s);
//            }
//            if (channelShell.isClosed()) {
//                System.out.println("exit-status:" + channelShell.getExitStatus());
//                break;
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        outputStream.close();
//        inputStream.close();
//        return result;
//    }


    /**
     * SFTP文件上传
     *
     * @param src 源地址
     * @param dst 目的地址
     * @throws Exception 上传文件失败
     */
//    public void putAndClose(String src, String dst) throws Exception {
//        putAndClose(src, dst, ChannelSftp.OVERWRITE);
//    }

    /**
     * SFTP文件上传
     *
     * @param src  源地址
     * @param dst  目的地址
     * @param mode 上传模式 默认为ChannelSftp.OVERWRITE
     * @throws Exception 上传文件失败
     */
//    public void putAndClose(String src, String dst, int mode) throws Exception {
//        put(src, dst, mode);
//        close();
//    }
//
//    public void put(String src, String dst) throws Exception {
//        put(src, dst, ChannelSftp.OVERWRITE);
//    }
//
//    public void put(String src, String dst, int mode) throws Exception {
//        initChannelSftp();
//        log.info("Upload File {} -> {}", src, dst);
//        channelSftp.put(src, dst, mode);
//        log.info("Upload File Success!");
//    }

    /**
     * SFTP文件上传并监控上传进度
     *
     * @param src 源地址
     * @param dst 目的地址
     * @throws Exception 上传文件失败
     */
//    public void putMonitorAndClose(String src, String dst) throws Exception {
//        putMonitorAndClose(src, dst, ChannelSftp.OVERWRITE);
//    }

    /**
     * SFTP文件上传并监控上传进度
     *
     * @param src  源地址
     * @param dst  目的地址
     * @param mode 上传模式 默认为ChannelSftp.OVERWRITE
     * @throws Exception 上传文件失败
     */
//    public void putMonitorAndClose(String src, String dst, int mode) throws Exception {
//        initChannelSftp();
//        FileProgressMonitor monitor = new FileProgressMonitor(new File(src).length());
//        log.info("Upload File {} -> {}", src, dst);
//        channelSftp.put(src, dst, monitor, mode);
//        log.info("Upload File Success!");
//        close();
//    }

    /**
     * SFTP文件下载
     *
     * @param src 源文件地址
     * @param dst 目的地址
     * @throws Exception 下载文件失败
     */
//    public void getAndClose(String src, String dst) throws Exception {
//        get(src, dst);
//        close();
//    }
//
//    public void get(String src, String dst) throws Exception {
//        initChannelSftp();
//        log.info("Download File {} -> {}", src, dst);
//        channelSftp.get(src, dst);
//        log.info("Download File Success!");
//    }

    /**
     * SFTP文件下载并监控下载进度
     *
     * @param src 源文件地址
     * @param dst 目的地址
     * @throws Exception 下载文件失败
     */
//    public void getMonitorAndClose(String src, String dst) throws Exception {
//        initChannelSftp();
//        FileProgressMonitor monitor = new FileProgressMonitor(new File(src).length());
//        log.info("Download File {} -> {}", src, dst);
//        channelSftp.get(src, dst, monitor);
//        log.info("Download File Success!");
//        close();
//    }

    /**
     * 删除指定目录文件
     *
     * @param path 删除路径
     * @throws Exception 远程主机连接异常
     */
//    public void deleteFile(String path) throws Exception {
//        initChannelSftp();
//        channelSftp.rm(path);
//        log.info("Delete File {}", path);
//    }

    /**
     * 删除指定目录
     *
     * @param path 删除路径
     * @throws Exception 远程主机连接异常
     */
//    public void deleteDir(String path) throws Exception {
//        initChannelSftp();
//        channelSftp.rmdir(path);
//        log.info("Delete Dir {} ", path);
//    }

    /**
     * 释放资源
     */
    public void close(String ip) {
        SshSession sshSession = SESSION_MANAGE.get(ip);
        if (ObjectUtils.isEmpty(sshSession)){
            return;
        }
        Session session = sshSession.getSession();
        Channel channel = sshSession.getChannel();
        ChannelExec channelExec = sshSession.getChannelExec();
//        if (channelSftp != null && channelSftp.isConnected()) {
//            channelSftp.disconnect();
//        }
        if (channelExec != null && channelExec.isConnected()) {
            channelExec.disconnect();
        }
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        SESSION_MANAGE.remove(ip);
    }

//    private void initChannelSftp() throws Exception {
//        channel = session.openChannel("sftp");
//        channel.connect(); // 建立SFTP通道的连接
//        channelSftp = (ChannelSftp) channel;
//        if (session == null || channel == null || channelSftp == null) {
//            log.error("请先执行init()");
//            throw new Exception("请先执行init()");
//        }
//    }

    private void initChannelExec(String ip) throws Exception {
        SshSession sshSession = SESSION_MANAGE.get(ip);
        log.info("IP:{}",ip);
        log.info("All Session:{}",SESSION_MANAGE);
//        if (ObjectUtils.isEmpty(sshSession)){
//
//        }
        Session session = sshSession.getSession();
        // 打开执行shell指令的通道
        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        sshSession.setChannel(channel);
        sshSession.setChannelExec(channelExec);
        SESSION_MANAGE.put(ip, sshSession);
    }

    private void initChannelShell(String ip) throws Exception {
        SshSession sshSession = SESSION_MANAGE.get(ip);
        Session session = sshSession.getSession();
        // 打开执行shell指令的通道
        Channel channel = session.openChannel("shell");
        ChannelShell channelShell = (ChannelShell) channel;
        sshSession.setChannelShell(channelShell);
        SESSION_MANAGE.put(ip, sshSession);
    }
}



