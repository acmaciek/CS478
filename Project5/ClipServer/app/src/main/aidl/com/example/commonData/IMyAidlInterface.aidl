// IMyAidlInterface.aidl
package com.example.commonData;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
     void playSong(int id);
     void pauseSong();
     void stopPlayer();
     List<String> songList();
     void resumePlaying();
}
