package com.example.roomdatabasedemo.roomdb

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec


@Database(entities = [Subscriber::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1 , to = 2 , spec = SubscriberDatabase.Migration1To2::class),
       AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration2To3::class)

    ],


)
abstract class SubscriberDatabase: RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    @RenameColumn(tableName = "subscriber_data_table", fromColumnName = "subscriber_name", toColumnName = "subs_id")
    class Migration1To2 : AutoMigrationSpec

    @RenameColumn(tableName = "subscriber_data_table", fromColumnName = "subscriber_id", toColumnName = "subscriber_name")
    class Migration2To3 : AutoMigrationSpec
    companion object{
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}