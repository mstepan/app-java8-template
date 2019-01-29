package com.max.app.dto;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;

import java.util.Date;
import java.util.List;

@Immutable
public final class UserData {
    final String name;
    final int age;

    final ImmutableList<String> list;

    @SuppressWarnings("Immutable")
    final Date dob;

    public UserData(String name, int age, List<String> list, Date dob) {
        this.name = name;
        this.age = age;
        this.list = ImmutableList.copyOf(list);
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return list;
    }
}
