package com.example.Gym.controller;

public class ResourceConflictException extends Throwable {
    public ResourceConflictException(Integer id, String username_already_exists) {
    }
}
