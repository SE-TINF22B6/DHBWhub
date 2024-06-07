import React from "react";
import './Report.css';
import Modal from "@mui/material/Modal";
import { useFormik } from 'formik';
import * as yup from 'yup';

interface ReportPostProps {
  reportOpen: boolean;
  setReportReason: React.Dispatch<React.SetStateAction<string>>;
  setReportDescription: React.Dispatch<React.SetStateAction<string>>;
  handleReportSubmit: () => void;
  handleClose: () => void;
}

export const Report: React.FC<ReportPostProps> = (props: ReportPostProps) => {
  const {
    reportOpen,
    setReportReason,
    setReportDescription,
    handleReportSubmit,
    handleClose,
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
    onSubmit: (values): void => {
      setReportReason(values.reportReason);
      setReportDescription(values.reportDescription);
      handleReportSubmit();
    },
  });

  if (!reportOpen) {
    return null;
  }

  return (
      <Modal open={reportOpen} onClose={handleClose}>
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
                onBlur={formik.handleBlur}
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
                onBlur={formik.handleBlur}
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