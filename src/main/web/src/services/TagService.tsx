import React, {Dispatch, SetStateAction} from "react";

export const handleTagInput = (
    event: React.KeyboardEvent<HTMLInputElement>,
    newTag: string,
    setTags: Dispatch<SetStateAction<string[]>>,
    setNewTag: Dispatch<SetStateAction<string>>,
    setTagError: Dispatch<SetStateAction<string>>,
    tags: string[]
): void => {
  if (event.key === 'Enter') {
    event.preventDefault();
    const trimmedTag: string = newTag.trim();
    let errorMessage: string = '';

    if (trimmedTag === '') {
      errorMessage = '🚫 Tag must not be empty.';
    } else if (trimmedTag.length === 1) {
      errorMessage = '🚫 Tag must be a word.';
    } else if (trimmedTag.length < 3) {
      errorMessage = '🚫 Tag must have at least 3 letters.';
    } else if (trimmedTag.split(' ').length !== 1) {
      errorMessage = '🚫 Tag must be a single word.';
    } else if (trimmedTag[0] !== trimmedTag[0].toUpperCase()) {
      errorMessage = '🚫 Tag must begin with a capital letter.';
    } else if (!/^[a-zA-ZäöüÄÖÜ]+$/.test(trimmedTag)) {
      errorMessage = '🚫 Tag must only contain letters.';
    } else if (/[äöüÄÖÜ]/.test(trimmedTag)) {
      errorMessage = '🚫 Tag must not contain umlauts.';
    } else if (trimmedTag.length > 12) {
      errorMessage = '🚫 Tag can have a maximum of 12 letters.';
    } else if (tags.includes(trimmedTag)) {
      errorMessage = '🚫 Tag already exists.';
    } else if (!/^[A-Z][a-z]*$/.test(trimmedTag)) {
      errorMessage = '🚫 Tag must start with a capital and only have lowercase letters afterwards.';
    } else {
      setTags([...tags, trimmedTag]);
      setNewTag('');
      setTagError('');
      return;
    }
    setTagError(errorMessage);
  }
};
