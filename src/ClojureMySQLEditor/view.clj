; ClojureMySQL Editor
;
; Technische Hochschule Mittelhessen
; Homepage: http://www.mni.thm.de
; Modul: Programmieren in Clojure
;
; Dieses Programm verbindet sich mit einer MySQL-Datenbank und stellt den Inhalt grafisch dar.
; Zusätzlich kann der Anwender Funktionen wie Bearbeiten, Hinzufügen, Löschen, Kommandozeile und Exportieren
; auf der Datenbank ausgeführt werden.
;
; @version     1.0.0
; @package     ClojureMySQLEditor
; @name        ClojureMySQLEditor.view
; @author      Niklas Simonis
; @author      Dominik Eller
; @description Diese Datei enthält Funktionen die Daten von der Datenbank holen und schreiben.
; @link        https://github.com/NiklasLM/clj-db-project

(ns ClojureMySQLEditor.view)

; Java-Klassen importieren
(import
  '(javax.swing ListSelectionModel JFileChooser DefaultCellEditor JFrame JLabel JTextField JButton JComboBox JTable JPanel JScrollPane JPasswordField JTextArea)
  '(javax.swing.table DefaultTableModel TableCellRenderer)
  '(javax.swing.event TableModelListener ListSelectionListener)
  '(java.awt.event ActionListener ItemListener)
  '(javax.swing.filechooser FileNameExtensionFilter)
  '(javax.swing.border EmptyBorder)
  '(java.util Vector)
  '(java.awt GridLayout Color GridBagLayout BorderLayout ComponentOrientation Dimension)
  )

; @name error-frame
; @description Erzeugt ein Fehlerfenster
; @param - err-reason - Enthält den Fehlergrund
; @return void
(defn error-frame
  [err-reason]
  (println (str "Error: " err-reason))
  (let [
        err-frame (JFrame. "Error Message")
        
        err-top-panel (JPanel.)
        err-top-label (JLabel. "Reason:")
        
        err-center-panel (JPanel.)
        err-center-label (JLabel. err-reason)
        
        err-footer-panel (JPanel.)
        err-footer-button (JButton. "Close")
       ]
    ; Farbe setzen
    (.setForeground err-top-label (. Color red))
    
    ;ActionListener für Close Button
    (.addActionListener
      err-footer-button
      (reify ActionListener
        (actionPerformed
          [_ evt]
          ; EVENT CMD
          (.setVisible err-frame false))))
    
    (doto err-top-panel
      (.add err-top-label))
    
    (doto err-center-panel
      (.add err-center-label))
    
    (doto err-footer-panel
      (.add err-footer-button))
    
    (doto err-frame
      (.add err-top-panel BorderLayout/PAGE_START)
      (.add err-center-panel BorderLayout/CENTER)
      (.add err-footer-panel BorderLayout/PAGE_END)
      (.setSize 350 175)
      (.setVisible true))))