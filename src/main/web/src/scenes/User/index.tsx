import React, {useState, useEffect} from "react";
import {useLocation} from "react-router-dom";
import {Header} from "../../organisms/header/Header";
import "./index.css";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import config from "../../config/config";
import {getJWT} from "../../services/AuthService";
import {UserDetail} from "./User";


export const User = () => {
    const location = useLocation();
    const searchParams: URLSearchParams = new URLSearchParams(location.search);
    const id: string | null = searchParams.get('id');
    const username: string | null = searchParams.get('name');
    const course: string | null = searchParams.get('course');
    const followers: string | null = searchParams.get('followers');
    const [userData, setUserData] = useState();

    const [notFound, setNotFound] = useState(false);
    const [loading, setLoading] = useState(true);
    const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

    const adBlockDetected: boolean = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);

    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    useEffect((): void => {
            const fetchUser = async (): Promise<void> => {

                    try {
                        let response;

                        /*if (searchParams.get('name')) {
                            response = await fetch(`${config.apiUrl}user/${searchParams.get('name')}`, {
                                headers: headersWithJwt
                            });*/
                        if (id) {
                            response = await fetch(`${config.apiUrl}user/${id}`, {
                                headers: headersWithJwt
                            });
                        }

                        if (response && response.ok) {
                            const userData = await response.json();
                            setUserData(userData);
                        } else {
                            setNotFound(true);
                        }
                        setLoading(false);
                    } catch (error) {
                        console.error("Fehler beim Abrufen der Nutzerdaten:", error);
                        setNotFound(true);
                        setLoading(false);
                    }
                }
            ;

            fetchUser();
        }
    )
    ;

    if (loading) {
        return (
            <div className="page">
                {adBlockDetected && <AdBlockOverlay/>}
                <Header/>
                <div className="loading-animation">
                    <Lottie animationData={animationData}/>
                </div>
                <Footer/>
                {isSmartphoneSize && <MobileFooter/>}
            </div>
        );
    }

    if (notFound || !username) {

    }
    const handleFollow = () => {
        // Logik für das Folgen hier einfügen
        console.log(`Du folgst ${username} jetzt!`);
    };

    return (
        <div className="page">
            {adBlockDetected && <AdBlockOverlay/>}
            <Header/>
            <div className="user-component">
                <div className="user-name">
                    {userData} <UserDetail accountId={4} image={null} course={null} followers={null} username={"strange"}></UserDetail>
                    {username}
                </div>
                <div className="user-name">
                    {id}
                </div>
                <div>
                    <p className="user-course">
                        Kurs: </p> {course}
                </div>
                <div>
                    <p className="user-followers">
                        followers: </p> {followers}

                </div>
                <button className="user-follow" onClick={handleFollow}>Follow</button>
            </div>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
        </div>

    );
};
