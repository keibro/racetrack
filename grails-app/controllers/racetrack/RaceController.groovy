package racetrack

class RaceController {
    def scaffold = Race

    def search = {
        render Race.search(params.q, params)
    }
}
