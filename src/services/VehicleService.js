import axios from 'axios'
import { BASE_SERVER_URL } from '../components/constants';

const VEHICLES_BASE_REST_API_URL = 'http://localhost:8080/api/vehicles/'
const USER_BASE_REST_API_URL = 'http://localhost:8080/api/auth'

class VehicleService {
    getAllVehicles(){ 
        return axios.get(BASE_SERVER_URL + 'api/vehicles/all')
    }

    saveCar(vehicle){
            return axios.post(VEHICLES_BASE_REST_API_URL + "auth/admin/add", vehicle)
            
    }

}

export default new VehicleService;