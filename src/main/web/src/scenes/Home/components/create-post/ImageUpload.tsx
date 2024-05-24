import React, {useState} from 'react';
import ImageUploading, {ImageType} from 'react-images-uploading';
import './ImageUpload.css';

interface ImageUploadProps {
  onImagesChange?: (images: ImageType[]) => void;
}

export const ImageUpload: React.FC<ImageUploadProps> = (props: ImageUploadProps) => {
  const {onImagesChange} = props;

  const [images, setImages] = useState<ImageType[]>([]);
  const [showButton, setShowButton] = useState(true);

  const onChange = (imageList: ImageType[]): void => {
    setImages(imageList);
    onImagesChange?.(imageList);
  };

  return (
      <div className="image-upload">
        <ImageUploading multiple value={images} onChange={onChange} maxNumber={1} dataURLKey="data_url">
          {({imageList, onImageUpload, onImageUpdate, onImageRemove, dragProps}) => {
            return (
                <div className="upload__image-wrapper">
                  {showButton && (
                      <button className="image-upload-button"
                              onClick={(): void => {
                                onImageUpload();
                                setTimeout((): void => {
                                      setShowButton(false)
                                    },
                                    1000);
                              }} {...dragProps}>
                        <img className="image-upload-picture" src={process.env.PUBLIC_URL + '/assets/home/create-post/upload.svg'}
                             alt="Upload"/>
                      </button>
                  )}
                  {imageList.map((image: ImageType, index: number) => (
                      <div key={index} className="image-item">
                        <img className="image-item__image" src={image['data_url']} alt="Upload" height="150"/>
                        <div className="image-item__btn-wrapper">
                          <button className="update-button" onClick={() => onImageUpdate(index)}>
                            <div className="update-button-label">Update</div>
                          </button>
                          <button className="remove-button" onClick={(): void => {
                            onImageRemove(index);
                            setShowButton(true)
                          }}>
                            <div className="remove-button-label">Remove</div>
                          </button>
                        </div>
                      </div>
                  ))}
                </div>
            );
          }}
        </ImageUploading>
      </div>
  );
};
