package com.quality.utils;

import com.jcraft.jsch.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SshSession {
    private Session session;

    private Channel channel;

    private ChannelExec channelExec;

    private ChannelSftp channelSftp;

    private ChannelShell channelShell;
}
