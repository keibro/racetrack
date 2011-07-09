package racetrack

class Registration {
    String name
    Date dateOfBirth
    String gender
    String address
    String city
    String state
    String zipcode
    String email
    Date dateCreated
    Date lastUpdated

    static mapping = {
        autoTimestamp false
    }

    static contraints = {
    }
}
