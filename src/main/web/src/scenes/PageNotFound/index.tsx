import React from "react";
import { Header } from "../../organisms/header/Header";
import "./index.css";
import Lottie from "lottie-react";
import errorAnimationData from "../../assets/error.json";

export const PageNotFound = () => {
    return (
        <div className="page-not-found">
          <Header/>
          <div className="error-animation">
            <Lottie animationData={errorAnimationData}/>
          </div>
        </div>
    );
}
