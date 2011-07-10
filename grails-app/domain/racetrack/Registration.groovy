package racetrack

class Registration {
    static constraints = {
        race()
        runner()
        paid()
        dateCreated()
    }

    static belongsTo = [race:Race, runner:Runner]

    Boolean paid
    Date dateCreated

    String toString(){
        "${id}, ${runner.lastName}"
    }
}
