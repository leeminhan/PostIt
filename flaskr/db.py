import sqlite3

import click
from flask import current_app,g # current_app is another special object that points to the app instance, g is a special object used to hold any data during a single app context
from flask.cli import with_appcontext # Wraps a callback to ensure the callback is executed with the script's application context.

def get_db(): 
    if 'db' not in g:
        g.db = sqlite3.connect( # Establishes a connection to file pointed to in 'DATABASE'
            current_app.config['DATABASE'],
            detect_types=sqlite3.PARSE_DECLTYPES
            )
        g.db.row_factory = sqlite3.Row

        return g.db # returns database connection

def close_db(e=None): # Checks if connection was created and closes it if it is.
    db = g.pop('db',None)

    if db is not None:
        db.close()

def init_db():
    db = get_db() # db = database connection
                    # open_resource opens a file relative to flaskr package
    with current_app.open_resource('schema.sql') as f: # with keyword is used when executed 2 related operations as a pair, automatically closes upon completion
        db.executescript(f.read().decode('utf8'))



@click.command('init-db')
@with_appcontext
def init_db_command():
    # Clearing existing data and create new tables
    init_db()
    click.echo('Initialised the database')


def init_app(app):
    app.teardown_appcontext(close_db) # app.teardown_appcontext() calls indicated function when cleaning up after returning response
    app.cli.add_command(init_db_command) # adds command under flask command




            
