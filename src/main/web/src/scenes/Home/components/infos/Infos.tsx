import React from "react";
import "./Infos.css";
import {Link} from "react-router-dom";

export const Infos = () => {
  return (
      <div className="infos">
        <Link className='faq' to={"/faq"}>
          <img className="faq-symbol" alt="" src={process.env.PUBLIC_URL + '/assets/home/infos/faq.svg'}/>
          <div className="faq-text">FAQ</div>
        </Link>
        <Link className='contact' to="/contact">
          <img className="contact-symbol" alt="" src={process.env.PUBLIC_URL + '/assets/home/infos/contact.svg'}/>
          <div className="contact-text">Contact</div>
        </Link>
        <Link className='privacy-policy' to={"/privacy-policy"}>
          <img className="privacy-policy-symbol" alt="" src={process.env.PUBLIC_URL + '/assets/home/infos/privacy-policy.svg'}/>
          <div className="privacy-policy-text">Privacy policy</div>
        </Link>
        <Link className='terms-of-service' to={"/terms-of-service"}>
          <img className="terms-of-service-symbol" alt="" src={process.env.PUBLIC_URL + '/assets/home/infos/tos.svg'}/>
          <div className="terms-of-service-text">Terms of service</div>
        </Link>
        <Link className='imprint' to={"/imprint"}>
          <img className="imprint-symbol" alt="" src={process.env.PUBLIC_URL + '/assets/home/infos/imprint.svg'}/>
          <div className="imprint-text">Imprint</div>
        </Link>
      </div>
  );
}