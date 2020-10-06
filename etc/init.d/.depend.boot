TARGETS = console-setup resolvconf mountkernfs.sh ufw plymouth-log apparmor screen-cleanup hostname.sh udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh checkroot.sh lvm2 open-iscsi networking iscsid checkfs.sh urandom mountdevsubfs.sh procps bootmisc.sh mountall.sh checkroot-bootclean.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
open-iscsi: networking iscsid
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
checkfs.sh: cryptdisks lvm2 checkroot.sh
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
bootmisc.sh: udev mountall-bootclean.sh mountnfs-bootclean.sh checkroot-bootclean.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
kmod: checkroot.sh
