package com.example.Notes.Log;

import com.example.Notes.enumeration.LOG;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Log {
    private String msg;
    private LOG logType;

    public static void WriteLog(LOG logType, String msg){
        Logger logger = LoggerFactory.getLogger(Log.class);
        switch (logType){
            case INFO -> logger.info(msg);
            case WARNING -> logger.warn(msg);
            case ERROR -> logger.error(msg);
        }
    }
}
