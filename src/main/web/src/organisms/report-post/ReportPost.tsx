import React from "react";
import './ReportPost.css';
import Modal from "@mui/material/Modal";

interface ReportPostProps {
  reportOpen: boolean;
  reportReason: string;
  reportDescription: string;
  setReportReason: React.Dispatch<React.SetStateAction<string>>;
  setReportDescription: React.Dispatch<React.SetStateAction<string>>;
  handleReportSubmit: () => void;
}

export const ReportPost: React.FC<ReportPostProps> = (props: ReportPostProps) => {
  const {
    reportOpen,
    reportReason,
    reportDescription,
    setReportReason,
    setReportDescription,
    handleReportSubmit,
  } = props;

  if (!reportOpen) {
    return null;
  }

  return (
      <Modal open={reportOpen} onClose={() => handleReportSubmit()}>
        <div className="report-popup">
          <div className="report-popup-header">Report Post</div>
          <label className="report-popup-field">Reason:</label>
          <br/>
          <input
              className="report-popup-input"
              type="text"
              value={reportReason}
              onChange={(e) => setReportReason(e.target.value)}/>
          <br/>
          <label className="report-popup-field">Description:</label>
          <br/>
          <textarea
              className="report-popup-textarea"
              value={reportDescription}
              onChange={(e) => setReportDescription(e.target.value)}/>
          <br/>
          <button className="report-popup-button" onClick={handleReportSubmit}>
            <div className="report-popup-button-label">Submit</div>
          </button>
        </div>
      </Modal>
  );
};