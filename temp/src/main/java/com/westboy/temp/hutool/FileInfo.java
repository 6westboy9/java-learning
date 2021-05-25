package com.westboy.temp.hutool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    protected String filename;
    protected long fileLength;
}
