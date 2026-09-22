cmake_minimum_required(VERSION 3.22)
project(graal_hello_launcher C)

add_library(launcher SHARED launcher.c)
target_link_libraries(launcher log dl)
