import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import TermsOfServiceInterface from "./TermsOfServiceInterface";

export const TermsOfService = () => {
    const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
legal_documents_pages
    return (
        <div className="tos-component">
            <Header/>
            <div className="tos-content">
                <TermsOfServiceInterface/>
            </div>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
};