import {Component} from "react";
import {Link} from "react-router";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../redux/action.js";

export default function HeadComponent() {

    const auth = useSelector(state => state.auth);
    const dispatch = useDispatch();

    const handleLogout = () => {
           dispatch(logout());
    }

    return (
        <>
            <nav className="navbar navbar-expand-sm bg-light">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">Navbar</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" to="/home">Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/students">Student manager</Link>
                            </li>
                            {!auth.account && <li className="nav-item">
                                <Link className="nav-link" to={'/login'}>Login</Link>
                            </li>}
                            {auth.account&& <li className="nav-item">
                                <button onClick={handleLogout} className="nav-link">Logout</button>
                            </li>}
                            <li className="nav-item">
                                <span className="nav-link">{auth?.account?.username}</span>
                            </li>

                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )

}