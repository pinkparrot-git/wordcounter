package service;

import java.util.Set;

public interface IFileUtilService {

    Set<String> loadWordsFromFile(String stopWordsFilePath);

}
