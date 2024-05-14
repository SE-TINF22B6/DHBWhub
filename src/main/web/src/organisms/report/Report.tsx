import React from "react";
import './Report.css';
import Modal from "@mui/material/Modal";
import { useFormik } from 'formik';
import * as yup from 'yup';

interface ReportPostProps {
  reportOpen: boolean;
  reportReason: string;
  reportDescription: string;
  setReportReason: React.Dispatch<React.SetStateAction<string>>;
  setReportDescription: React.Dispatch<React.SetStateAction<string>>;
  handleReportSubmit: () => void;
}

export const Report: React.FC<ReportPostProps> = (props: ReportPostProps) => {
  const {
    reportOpen,
    reportReason,
    reportDescription,
    setReportReason,
    setReportDescription,
    handleReportSubmit,
  } = props;

  const validationSchema = yup.object().shape({
    reportReason: yup.string().required('🚫 Reason is required'),
  });

  const formik = useFormik({
    initialValues: {
      reportReason: '',
      reportDescription: '',
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      setReportReason(values.reportReason);
      setReportDescription(values.reportDescription);
      handleReportSubmit();
    },
  });

  if (!reportOpen) {
    return null;
  }

  return (
      <Modal open={reportOpen} onClose={() => handleReportSubmit()}>
        <div className="report-popup">
          <div className="report-popup-header">Report Form</div>
          <form className="report-form" onSubmit={formik.handleSubmit}>
            <label className="report-popup-field">Reason:</label>
            <br/>
            <input
                className="report-popup-input"
                type="text"
                name="reportReason"
                value={formik.values.reportReason}
                onChange={formik.handleChange}
            />
            {formik.errors.reportReason && formik.touched.reportReason && (
                <div className="report-error">{formik.errors.reportReason}</div>
            )}
            <br/>
            <label className="report-popup-field">Description:</label>
            <br/>
            <textarea
                className="report-popup-textarea"
                name="reportDescription"
                value={formik.values.reportDescription}
                onChange={formik.handleChange}
            />
            <br/>
            <button className="report-popup-button" type="submit">
              <div className="report-popup-button-label">Submit</div>
            </button>
          </form>
        </div>
      </Modal>
  );
};
