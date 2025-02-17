
import { ReactNode } from 'react';
import classes from './Layout.module.scss';
export function Layout({children,className}:{children:ReactNode,className:string}){
    return (<div className={`${classes.root} ${className}`}>
        <header className={classes.container}>
            <a href="/">
            <img src="vite.svg" alt="" className={classes.logo} />
            </a>
        </header>
        <main
        className={classes.container}>{children}</main>
        <footer>
            <ul className={classes.container}></ul>
            <li>
                <img src="/logo-dark.svg" alt="" />
            </li>
            <li>
                <a href="">Accessiblity</a>
            </li>
            <li>
                <a href="">Privacy Policy</a>
            </li>
            <li>
                <a href="">Terms of Service</a>
            </li>
            <li>
                <a href="">Cookie Policy</a>
            </li>
            <li>
                <a href="">Advertise</a>
            </li>
            <li>
                <a href="">Copywrite Policy</a>
            </li>

        </footer>



    </div>
    );
}
