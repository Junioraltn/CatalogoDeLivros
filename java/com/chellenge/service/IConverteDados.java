package com.challenge.chellenge.service;

public interface IConverteDados {
    <T> T obterDados(String json,Class<T> classe);
}
