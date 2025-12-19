
import './App.css'
import HeadComponent from "./component/HeadComponent.jsx";
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap/dist/js/bootstrap.js"
import ListComponent from "./component/ListComponent.jsx";
import {Route, Routes} from "react-router";
import HomeComponent from "./component/HomeComponent.jsx";
import AddComponent from "./component/AddComponent.jsx";
import {ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import LoginComponent from "./component/login/LoginComponent.jsx";

function App() {


  return (
    <>
     <HeadComponent/>
        <Routes>
            <Route path={'/'} element={<HomeComponent/>}/>
            <Route path={'/home'} element={<HomeComponent/>}/>
            <Route path={'/login'} element={<LoginComponent/>}/>
            <Route path={'/students'} element={<ListComponent/>}/>
            <Route path={'/students/add'} element={<AddComponent/>}/>
        </Routes>
        <ToastContainer/>
    </>
  )
}

export default App
