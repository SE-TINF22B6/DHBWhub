import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import Typography from "@mui/material/Typography";
import {faqData} from "./FaqData";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import AccordionDetails from "@mui/material/AccordionDetails";

export const FAQ = () => {
    const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
    return (
        <div className="faq-component">
            <Header/>
            <div className="faq-content">
                <Typography variant="h5">
                    Frequently Asked Questions
                </Typography>
                <div className= "faq-accordion">
                {faqData.map((item, index) => (
                    <Accordion key={index} className="accordion-item">
                        <AccordionSummary expandIcon={<ExpandMoreIcon style={{color: 'fff'}}/>}
                                          className="accordion-summary">
                            <Typography>{item.question}</Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Typography>{item.answer}</Typography>
                        </AccordionDetails>
                    </Accordion>
                ))}
                </div>
            </div>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
};
