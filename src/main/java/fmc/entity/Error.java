package fmc.entity;

import java.util.Map;

public class Error {

    public final static Map<ErrorCode, ErrorMessage> CONSTANTS = Map.of(
        ErrorCode.Exception, ErrorMessage.Exception,
        ErrorCode.Request, ErrorMessage.Request
    );

    public enum ErrorCode {
        Exception("100"),
        Request("040"),
        Logic("020");
        
        private final String value;
        // 

        ErrorCode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ErrorMessage {
        Exception("Внутренняя ошибка"),
        Request("Некоректный запрос: "),
        Logic("");
        
        private final String value;

        ErrorMessage(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}