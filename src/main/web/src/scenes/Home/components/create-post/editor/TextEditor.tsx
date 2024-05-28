import React, {useEffect} from 'react';
import ExampleTheme from './Theme';
import {LexicalComposer} from '@lexical/react/LexicalComposer';
import {RichTextPlugin} from '@lexical/react/LexicalRichTextPlugin';
import {ContentEditable} from '@lexical/react/LexicalContentEditable';
import {HistoryPlugin} from '@lexical/react/LexicalHistoryPlugin';
import LexicalErrorBoundary from '@lexical/react/LexicalErrorBoundary';
import ToolbarPlugin from './ToolbarPlugin';
import {HeadingNode, QuoteNode} from '@lexical/rich-text';
import {TableCellNode, TableNode, TableRowNode} from '@lexical/table';
import {ListItemNode, ListNode} from '@lexical/list';
import {CodeHighlightNode, CodeNode} from '@lexical/code';
import {AutoLinkNode, LinkNode} from '@lexical/link';
import './TextEditor.css';
import {useLexicalComposerContext} from "@lexical/react/LexicalComposerContext";
import {EditorState} from "lexical";

function Placeholder(): React.JSX.Element {
  return <div className="editor-placeholder">Enter some text...</div>;
}

const editorConfig = {
  theme: ExampleTheme,
  onError(error: Error) {
    throw error;
  },
  namespace: 'editor',
  nodes: [
    HeadingNode,
    ListNode,
    ListItemNode,
    QuoteNode,
    CodeNode,
    CodeHighlightNode,
    TableNode,
    TableCellNode,
    TableRowNode,
    AutoLinkNode,
    LinkNode,
  ],
};

interface TextEditorProps {
  onEditorStateChange: (editorState: EditorState) => void;
}

function OnChangePlugin({ onChange }: { onChange: (editorState: any) => void }) {
  const [editor] = useLexicalComposerContext();
  useEffect(() => {
    return editor.registerUpdateListener(({ editorState }) => {
      onChange(editorState);
    });
  }, [editor, onChange]);
  return null;
}

export const TextEditor: React.FC<TextEditorProps> = (props: TextEditorProps) => {
  const {onEditorStateChange } = props;

  function onChange(newEditorState: EditorState) {
    onEditorStateChange(newEditorState);
  }

  return (
      <LexicalComposer initialConfig={editorConfig}>
        <div className="editor-container">
          <ToolbarPlugin />
          <div className="editor-inner">
            <RichTextPlugin
                contentEditable={<ContentEditable className="editor-input"/>}
                placeholder={<Placeholder />}
                ErrorBoundary={LexicalErrorBoundary}
            />
            <HistoryPlugin/>
            <OnChangePlugin onChange={onChange}/>
          </div>
        </div>
      </LexicalComposer>
  );
};

export default TextEditor;