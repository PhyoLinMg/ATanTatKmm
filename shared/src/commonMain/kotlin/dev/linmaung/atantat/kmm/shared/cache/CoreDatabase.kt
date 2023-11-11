package dev.linmaung.atantat.kmm.shared.cache

import Student


class CoreDatabase(driverFactory:DriverFactory){

    private val aTanTatDatabase=ATanTatDatabase(driverFactory.createDriver())
    private val dbQuery= aTanTatDatabase.aTanTatDatabaseQueries

    fun clearStudent(id:Int){
        dbQuery.transaction {
            dbQuery.deleteStudent(id=id.toLong())
        }
    }

    fun insertStudent(name:String,className:String){
        dbQuery.insertStudent(name = name,className=className)
    }

    fun getStudentList(): List<Student>{
        return dbQuery.selectAllStudents(::mapStudents).executeAsList()
    }

    private fun mapStudents(name:String,className:String):Student{
        return Student(name,className)
    }


}