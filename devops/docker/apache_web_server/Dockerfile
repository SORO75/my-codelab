    # Verwende ein offizielles Apache-Image als Basis
    FROM httpd:latest
    # Kopiere deine Website-Dateien in das Apache-Dokumenten-Root-Verzeichnis
    COPY . /usr/local/apache2/htdocs/
    # Lege den Port fest, auf dem Apache lauscht
    EXPOSE 80
    # Starte Apache im Vordergrund
    CMD ["httpd", "-D", "FOREGROUND"]
