
import './App.css';
import { Toolbar, AppBar, Typography, List } from '@mui/material';
import ListVehicles from './components/ListVehicles';
import Header from './components/Header';
import RegistrationForm from './components/RegistrationForm';
import LoginForm from './components/LoginForm';
import AddCar from './components/AddCar';
import { BrowserRouter, Routes, Route, Link} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Header/>
      <BrowserRouter>
          <Routes>
              <Route path='/api/vehicles' element={<ListVehicles/>} />
              <Route path='/auth/add' element={<AddCar />} />
              <Route path='/login' element={<LoginForm />} />
              <Route path='/register' element={<RegistrationForm />} />
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
