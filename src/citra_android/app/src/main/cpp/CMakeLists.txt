cmake_minimum_required(VERSION 3.22.1)

add_library(citra_android SHARED citra.cpp)

target_link_libraries(citra_android audio_core citra_common citra_core input_common network)
target_link_libraries(citra_android android camera2ndk inih jnigraphics log mediandk yuv)

set(CPACK_PACKAGE_EXECUTABLES ${CPACK_PACKAGE_EXECUTABLES} citra_android)