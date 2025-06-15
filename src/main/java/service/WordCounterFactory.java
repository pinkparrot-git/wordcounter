package service;

import configuration.WordCounterConfiguration;

public class WordCounterFactory {
    public static WordCounterService create(IFileUtilService fileUtilService, WordCounterConfiguration config) {
        WordCounterService service = new WordCounterService(fileUtilService, config);
        service.initialize();
        return service;
    }
}
