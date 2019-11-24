import os

from flask import Flask,jsonify

# Application factory refers to the structure whereby app entry point sits atop all other parts of the application
def create_app(test_config=None):
    # create and configure the app
    app = Flask(__name__, instance_relative_config=True) # Flask object
    app.config.from_mapping(
        SECRET_KEY='dev',
        DATABASE=os.path.join(app.instance_path, 'flaskr.sqlite'),
    )

    if test_config is None:
        # Load the instance config, if it exists, when not testing
        app.config.from_pyfile('config.py', silent=True)
    else:
        # Load the test config if passed in
        app.config.from_mapping(test_config)

    # ensure the instance floder exists
    try:
        os.makedirs(app.instance_path)
    except OSError:
        pass

    from . import db
    db.init_app(app)

    from . import auth
    app.register_blueprint(auth.bp)

    from . import blog
    app.register_blueprint(blog.bp)
    app.add_url_rule('/', endpoint='index') # Equivalent to @app.route('/') to def index(). This essentially maps the url to our view function
                                            # Endpoints are essentially names used for reverse-lookup of view functions, eg: url_for('end-point'). You can rename endpoints
    return app
