import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css';

export const Footer = () => {
  return (
      <footer className="footer-component">
        <div className="footer-content">
          <div className="md:flex md:items-center md:justify-between">
            <ul className="footer-links">
              <li className="footer-links-item">
                <Link to="https://github.com/SE-TINF22B6/DHBWhub/" target="_blank" className="footer-link" aria-label="Github">
                  <img width="20" height="20" src={process.env.PUBLIC_URL + '/assets/footer/github.svg'} alt="Github"/>
                </Link>
                <Link to="https://www.paypal.me/dhbwhub" target="_blank" className="footer-link" aria-label="PayPal">
                  <img width="20" height="20" src={process.env.PUBLIC_URL + '/assets/footer/paypal.svg'} alt="PayPal"/>
                </Link>
                <Link to="https://www.instagram.com/dhbwhub" target="_blank" className="footer-link" aria-label="Instagram">
                  <img width="20" height="20" src={process.env.PUBLIC_URL + '/assets/footer/instagram.svg'} alt="Instagram"/>
                </Link>
              </li>
            </ul>
            <div className="footer-text">&copy; DHBWhub – All rights reserved.</div>
          </div>
        </div>
      </footer>
  )
}
